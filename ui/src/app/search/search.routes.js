routes.$inject = ['$stateProvider'];
export default function routes($stateProvider) {
  $stateProvider
  .state('searchState', {
    url: '/search',
    component: 'searchComponent'
  })
}