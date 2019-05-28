package org.nunocky.randomuser

import java.io.Serializable

class RandomUserResponse {
    var results: List<User>? = null
    var info: Info? = null

    inner class User : Serializable {
        fun fullName(): String {
            return "${name!!.first} ${name!!.last}"
        }

        var gender: String? = null
        var name: Name? = null
        var location: Location? = null
        var email: String? = null
        var login: Login? = null
        var dob: Dob? = null
        var registered: Registered? = null
        var phone: String? = null
        var cell: String? = null
        var id: Id? = null
        var picture: Picture? = null
        var nat: String? = null

        inner class Name : Serializable {
            var title: String? = null
            var first: String? = null
            var last: String? = null
        }

        inner class Location : Serializable {
            var street: String? = null
            var city: String? = null
            var state: String? = null
            //var postcode: Long = 0
            var postcode: String? = null
            var coordinates: Coordinates? = null
            var timezone: TimeZone? = null
        }

        inner class Coordinates : Serializable {
            //var latitude: String? = null
            //var longitude: String? = null
            var latitude: Float = 0.toFloat()
            var longitude: Float = 0.toFloat()
        }

        inner class TimeZone : Serializable {
            var offset: String? = null
            var description: String? = null
        }

        inner class Login : Serializable {
            var uuid: String? = null
            var username: String? = null
            var password: String? = null
            var salt: String? = null
            var md5: String? = null
            var sha1: String? = null
            var sha256: String? = null
        }

        inner class Dob : Serializable {
            var date: String? = null
            var age: Int = 0
        }

        inner class Registered : Serializable {
            var date: String? = null
            var age: Int = 0
        }

        inner class Id : Serializable {
            var name: String? = null
            var value: String? = null
        }

        inner class Picture : Serializable {
            var large: String? = null
            var medium: String? = null
            var thumbnail: String? = null
        }
    }

    inner class Info {
        var seed: String = ""
        var results = 0
        var page = 0
        var varsion = ""
    }
}