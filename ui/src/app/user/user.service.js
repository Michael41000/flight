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