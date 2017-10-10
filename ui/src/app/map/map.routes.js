routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('flightMapState', {
    url: '/flightMap',
    component: 'flightMap'
  })
}