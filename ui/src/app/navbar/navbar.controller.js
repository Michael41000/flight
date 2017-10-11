/* @ngInject */
class NavbarController {

    constructor($user, $state) {
        this.$user = $user
        this.$state = $state
        console.log(this.$user.isLoggedIn)

    }

    logout() {
        this.$user.logout();
        this.$state.go('allFlightsState')
    }

}

export default NavbarController