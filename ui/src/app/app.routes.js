routes.$inject = ['$urlRouterProvider', '$stateProvider', '$locationProvider'];

export default function routes($urlRouterProvider, $stateProvider, $locationProvider) {

    $stateProvider
    .state('allFlightsState', {
      url: '/flights',
      component: 'flightListComponent',
      resolve: {
        flights: ['$flights', function ($flights) {
            return $flights.getFlights().then((done) => {
                return done
            })
        }]
    }
    })

    $urlRouterProvider.otherwise('/flights')

    $locationProvider.hashPrefix('')

}