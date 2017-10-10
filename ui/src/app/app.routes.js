routes.$inject = ['$urlRouterProvider', '$stateProvider', '$locationProvider'];

export default function routes($urlRouterProvider, $stateProvider, $locationProvider) {

    $stateProvider
    .state('homeState', {
      url: '/home',
      component: 'flightApp'
    })

    $urlRouterProvider.otherwise('/itinerary')

    $locationProvider.hashPrefix('')

}