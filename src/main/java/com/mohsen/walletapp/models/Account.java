package com.mohsen.walletapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


//@Document("account")
//@JsonIgnoreProperties(ignoreUnknown = true)
//@EqualsAndHashCode(callSuper = true)
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Table //is a corresponding table that matches that entity in the database
@Entity // for specifies class is an entity and is mapped to a database table.
@Data // for getter and seter
public class Account implements Serializable {

    private static final long serialVersionUID = 2L;

    @Size(max = 50)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String accountUUID;

    @Column(unique = true)
    private String userUUID;

    @Column(name = "account_name", nullable = false)
    private String account_name;

    @Column(name = "account_number", nullable = false)
    private String account_number;

    @Column(name = "bank_name", nullable = false)
    private String bank_name;

}
