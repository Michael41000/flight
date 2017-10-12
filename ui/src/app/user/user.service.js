/* @ngInject */
class UserService {
    constructor($http, apiUrl, $state, $cookies) {
        this.$http = $http
        this.apiUrl = apiUrl
        this.$state = $state
        this.$cookies = $cookies

        this.username

        this.password

        this.isLoggedIn = false
    }

    checkUserCredentials(username, password) {
        return this.$http
            .post(`${this.apiUrl}/users/checkUserCredentials/` + username, { username, password })
            .then(result => result.data)
    }

    checkUsernameAvailable(username) {
        return this.$http
            .get(`${this.apiUrl}/users/checkUsernameAvailable/` + username)
            .then(result => result.data)
    }

    makeUser(username, password) {
        return this.$http
            .post(`${this.apiUrl}/users/`, { username, password })
            .then(result => result.data)
    }

    saveItinerary(itineraryCredential) {
        return this.$http
            .post(`${this.apiUrl}/users/itineraries/`, itineraryCredential)
            .then(result => result.data)
    }

    getItineraries(username) {
        return this.$http
            .get(`${this.apiUrl}/users/itineraries/` + username)
            .then(result => result.data)
    }

    getItinerariesByOriginStartsWith(username, origin) {
        return this.$http
            .get(`${this.apiUrl}/users/itineraries/` + username + '/origin/' + origin)
            .then(result => result.data)
    }

    getItinerariesByDestinationStartsWith(username, destination) {
        return this.$http
            .get(`${this.apiUrl}/users/itineraries/` + username + '/destination/' + destination)
            .then(result => result.data)
    }

    getItinerariesByOriginStartsWithAndDestinationStartsWith(username, origin, destination) {
        return this.$http
            .get(`${this.apiUrl}/users/itineraries/` + username + '/origin/' + origin + '/destination/' + destination)
            .then(result => result.data)
    }

    deleteItinerary(id, credential) {
        return this.$http({
            url: `${this.apiUrl}/users/itineraries/` + id,
            method: 'DELETE',
            data: credential,
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        })
    }

    login(username, password) {
        this.username = username
        this.password = password
        this.$cookies.put('username', this.username)
        this.$cookies.put('password', this.password)
        this.isLoggedIn = true
        this.$state.go('flightListState', {
            reload: true
        })
    }

    logout() {
        this.username = undefined
        this.password = undefined
        this.$cookies.remove('username')
        this.$cookies.remove('password')
        this.isLoggedIn = false
        this.$state.go('signInSignUpState', {
            reload: true
        })
    }

}


export default UserService