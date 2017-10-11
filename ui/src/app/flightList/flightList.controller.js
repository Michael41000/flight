/* @ngInject */
class FlightListController {
    constructor() {
        this.mapisShown = false
    }

    showMap() {
        this.mapisShown = true;
    }

    hideMap() {
        this.mapisShown = false;
    }
}

export default FlightListController