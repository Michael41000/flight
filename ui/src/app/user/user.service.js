/* @ngInject */
class UserService {
    constructor($http, apiUrl, $state) {
        this.$http = $http
        this.apiUrl = apiUrl
        this.$state = $state

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

    login(username, password) {
        this.username = username
        this.password = password
        this.isLoggedIn = true
        this.$state.go('allFlightsState')
    }

    logout() {
        this.username = undefined
        this.password = undefined
        this.isLoggedIn = false
    }

}

export default UserService