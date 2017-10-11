import templateUrl from './app.component.html'

/* @ngInject */
class AppController {
  constructor($log, $user, $cookies, $stomp, apiUrl, $rootScope) {
    $log.debug('AppController is a go.')
    if ($cookies.get('username') !== undefined && $cookies.get('password') !== undefined) {
      $user.login($cookies.get('username'), $cookies.get('password'))
    }

    const socket = new SockJS(apiUrl + '/websocket')
    const stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/change/flights', function (greeting) {
        //console.log("in subscribe")  
        //console.log(greeting.body)
        $rootScope.$broadcast('flightsChanged')
      })
    })

    

  }

}

export default {
  templateUrl,
  controller: AppController,
  controllerAs: '$appCtrl'
}
