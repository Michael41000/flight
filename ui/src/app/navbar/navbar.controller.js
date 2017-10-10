/* @ngInject */
class NavbarController {

    constructor($navbar, $user, $state) {
        this.$navbar = $navbar
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