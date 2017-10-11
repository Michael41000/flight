routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('userItinerariesState', {
    url: '/myItineraries',
    component: 'userItinerariesComponent'
  })
}