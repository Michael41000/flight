routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('itineraryListState', {
    url: '/itineraries',
    component: 'itineraryListComponent'
  })
}