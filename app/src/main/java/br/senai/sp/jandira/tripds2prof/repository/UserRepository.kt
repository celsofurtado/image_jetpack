package br.senai.sp.jandira.tripds2prof.repository

import android.content.Context
import br.senai.sp.jandira.tripds2prof.dao.TripDb
import br.senai.sp.jandira.tripds2prof.model.User

class UserRepository(context: Context) {

    private val db = TripDb.getDatabase(context)

    fun save(user: User): Long {
        return db.userDao().save(user)
    }

    fun findUserByEmail(email: String): User {
        return db.userDao().findUserByEmail(email)
    }

}