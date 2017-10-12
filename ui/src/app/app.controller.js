/* @ngInject */
class AppController {
    constructor($log, $user, $cookies, apiUrl, $rootScope) {
        $log.debug('AppController is a go.')
        if ($cookies.get('username') !== undefined && $cookies.get('password') !== undefined) {
            $user.login($cookies.get('username'), $cookies.get('password'))
        }

        // Connect to websocket to see when flights change and broadcast in rootScope
        const socket = new SockJS(apiUrl + '/websocket')
        const stompClient = Stomp.over(socket)
        //stompClient.debug = null
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/change/flights', function (greeting) {
                $rootScope.$broadcast('flightsChanged')
            })
        })
    }
}

export default AppController