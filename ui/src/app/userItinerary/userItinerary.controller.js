/* @ngInject */
class UserItineraryController {
    constructor($user, $state) {
        this.mapisShown = false
        this.$user = $user
        this.$state = $state
    }

    showMap() {
        this.mapisShown = true;
    }

    hideMap() {
        this.mapisShown = false;
    }
}

export default UserItineraryController