package com.learn.DecProjectModule.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGatewayImplementation implements  PaymentService{

    @Override
    public String makePayment(String orderId, Long amount) throws StripeException {
        //1. Create priceCreate Param Object -> INR, Amount, orderId and
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");;

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();

        Price price = Price.create(params);

        //Creat the payment link
        PaymentLinkCreateParams linkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(linkParams);

        return paymentLink.getUrl();
    }
}
