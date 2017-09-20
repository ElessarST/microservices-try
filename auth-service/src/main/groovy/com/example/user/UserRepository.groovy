package com.example.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
@Repository
interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email)
}