routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('signInSignUpState', {
    url: '/signInSignUp',
    component: 'signInSignUpComponent'
  })
}