routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('flightListState', {
    url: '/flights',
    component: 'flightListComponent'
  })
}