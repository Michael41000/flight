/* @ngInject */
class MapController {
    zoom = 7
    center = [35.5175, -86.5804]
    markers = []
    paths = []

    constructor($map, locations) {
        this.$map = $map

        this.colors = ['#CC0099', '#AA1100', '#FF3388']
    }

    $onInit() {
        console.log(this.flights)
        this.$map.getLocations().then((done) => {
            console.log(done)
            this.locations = done
            this.locations.forEach((location) => {
                this.addMarker(location)
            })
            this.flights.forEach((flight, index) => {
                const flightOrigin = this.locations.find(location => location.city.toLowerCase() === flight.origin.toLowerCase())
                const flightDestination = this.locations.find(location => location.city.toLowerCase() === flight.destination.toLowerCase())
                this.addPath(flightOrigin, flightDestination, this.colors[index])
            })
        })
    }

    addMarker({ latitude, longitude }) {
        this.markers.push({
            position: `[${latitude}, ${longitude}]`
        })
    }

    addPath(a, b, color) {
        this.paths.push({
            path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
            strokeColor: color,
            strokeOpacity: 1.0,
            strokeWeight: 3,
            geodesic: true
        })
    }
}

export default MapController