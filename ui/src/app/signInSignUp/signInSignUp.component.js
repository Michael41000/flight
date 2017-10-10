import templateUrl from './signInSignUp.component.html'

/* @ngInject */
class SignInSignUpController {

  constructor($user) {
    this.$user = $user

    this.username = ''

    this.password = ''

    this.failedSignIn = false
  }

  signIn = () => {
    this.$user.checkUserCredentials(this.username, this.password).then((done) => {
      console.log(done)
      if (done) {
        this.$user.login(this.username, this.password)
        this.failedSignIn = false
      }
      else {
        this.failedSignIn = true
      }
    })
  }

  signUp = () => {
    this.$user.checkUsernameAvailable(this.username).then((done) => {
      console.log(done)
      if (done === true) {
        this.$user.makeUser(this.username, this.password).then((done) => {
          this.$user.login(this.username, this.password)
        })
        this.failedSignUp = false
      }
      else {
        this.failedSignUp = true
      }
    })
  }

}

export default {
  templateUrl,
  controller: SignInSignUpController,
  controllerAs: '$signInSignUpCtrl'
}