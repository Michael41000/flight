routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('itineraryState', {
    url: '/itinerary',
    component: 'itineraryComponent'
  })
}