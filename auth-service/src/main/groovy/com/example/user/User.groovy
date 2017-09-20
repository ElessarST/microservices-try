package com.example.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
@Entity(name = "auth_user")
class User implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id

    String email

    String password

}
