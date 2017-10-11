routes.$inject = ['$urlRouterProvider', '$stateProvider', '$locationProvider'];

export default function routes($urlRouterProvider, $stateProvider, $locationProvider) {

    $urlRouterProvider.otherwise('/flights')

    $locationProvider.hashPrefix('')

}