/* @ngInject */
class MapController {
    zoom = 7
    center = [35.5175, -86.5804]
    markers = []
    paths = []

    constructor($map) {
        this.$map = $map
        this.colors = ['#CC0099', '#AA1100', '#FF3388']
        this.googleMapsUrl = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBZvZmFk5Ut1EXgORPuYin62ZdCVQ8d5J8"
    }

    $onInit() {
        console.log(this.flights)
        this.$map.getLocations().then((done) => {
            console.log(done)
            this.locations = done
            this.flights.forEach((flight, index) => {
                const flightOrigin = this.locations.find(location => location.city.toLowerCase() === flight.origin.toLowerCase())
                const flightDestination = this.locations.find(location => location.city.toLowerCase() === flight.destination.toLowerCase())
                // Dont add duplicate markers
                if (index === 0) {
                    this.addMarker(flightOrigin)
                    this.addMarker(flightDestination)
                }
                else {
                    this.addMarker(flightDestination)
                }

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
            icons: [{
                icon: {
                    path: 'M 0,0 2,2 M 0,0 -2,2',
                    strokeColor: color,
                    strokeWeight: 5
                },
                offset: '100%'
            }],
            strokeWeight: 3,
            geodesic: true
        })
    }
}

export default MapController