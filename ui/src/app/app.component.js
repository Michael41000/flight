import templateUrl from './app.component.html'

/* @ngInject */
class AppController {
  constructor($log, $user, $cookies) {
    $log.debug('AppController is a go.')
    if ($cookies.get('username') !== undefined && $cookies.get('password') !== undefined)
    {
        $user.login($cookies.get('username'), $cookies.get('password'))
    }
  }
}

export default {
  templateUrl,
  controller: AppController,
  controllerAs: '$appCtrl'
}
