package com.mohsen.walletapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Transactions implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, name = "tran_ref")
    private String tranRef;
    @Column(name = "merchant_id")
    private float merchantId;
    @Column(name = "profile_id")
    private float profileId;
    @Column(name = "cart_id")
    private String cartId;
    @Column(name = "cart_description")
    private String cartDescription;
    @Column(name = "cart_currency")
    private String cartCurrency;
    @Column(name = "cart_amount")
    private String cartAmount;
    @Column(name = "tran_currency")
    private String tranCurrency;
    @Column(name = "tran_total")
    private String tranTotal;
    @Column(name = "tran_type")
    private String tranType;
    @Column(name = "tran_class")
    private String tranClass;
    @Column(name = "ipn_trace")
    private String ipnTrace;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_details_id", referencedColumnName = "id")
    private CustomerDetails customerDetails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_result_id", referencedColumnName = "id")
    private PaymentResult paymentResult;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_info_id", referencedColumnName = "id")
    private PaymentInfo paymentInfo;
}
