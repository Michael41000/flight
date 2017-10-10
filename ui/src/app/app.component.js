import templateUrl from './app.component.html'

/* @ngInject */
class AppController {
  constructor($log) {
    $log.debug('AppController is a go.')
    this.flights = [{
      origin: 'Memphis',
      destination: 'Nashville'
    },
    {
      origin: 'Nashville',
      destination: 'Knoxville'
    }]
  }
}

export default {
  templateUrl,
  controller: AppController,
  controllerAs: '$appCtrl'
}
