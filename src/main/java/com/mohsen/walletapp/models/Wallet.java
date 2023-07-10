package com.mohsen.walletapp.models;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

//@Document("wallet")
/*@Transactional
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor*/
@Getter
@Setter
@Builder
@Entity
@Table(name = "wallet")
@AllArgsConstructor
@NoArgsConstructor
public class Wallet implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String walletUUID;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.ALL)
    //@JsonIgnoreProperties(value = "user_id", allowGetters = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

   /* public void setId(Long id) {
        this.id = id;
    }

    *//*    public Wallet() {super();}*//*


    public String getWalletUUID() {
        return walletUUID;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setWalletUUID(String walletUUID) {
        this.walletUUID = walletUUID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", walletUUID='" + walletUUID + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
*/
}
