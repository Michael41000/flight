/* @ngInject */
class UserService {
    constructor ($http, apiUrl) {
      this.$http = $http
      this.apiUrl = apiUrl
    }
  
    checkUserCredentials(username, password) {
        return this.$http
            .post(`${this.apiUrl}/users/checkUserCredentials/` + username, {username, password})
            .then(result => result.data)
    }

    checkUsernameAvailable(username) {
        return this.$http
        .get(`${this.apiUrl}/users/checkUsernameAvailable/` + username)
        .then(result => result.data)
    }

    makeUser(username, password) {
        return this.$http
        .post(`${this.apiUrl}/users/`, {username, password})
        .then(result => result.data)
    }

  }
  
  export default UserService