async function fetchHikingRoute(waypoints) {
    const apiKey = '5b3ce3597851110001cf62485ca8ad3559e045a288a2bf868a1afd96';
    const url = 'https://api.openrouteservice.org/v2/directions/foot-hiking/geojson';

    console.log("waypoints in fetch: ", waypoints);
    const formattedWaypoints = waypoints.map(wp => [wp.lng, wp.lat]);

    const body = {
        coordinates: formattedWaypoints,
        format: 'geojson',
        elevation: true
    };

    console.log(JSON.stringify(body));

    const response = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
            'Accept': 'application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8',
            'Content-Type': 'application/json; charset=utf-8',
            'Authorization': apiKey
        }
    });

    console.log("response: ", JSON.stringify(response));
    console.log(response);

    if (!response.ok) {
        const message = `An error occurred: ${response.statusText}`;
        throw new Error(message);
    }

    const responseData = await response.json();

    // Assuming the response includes sections with detailed statistics
    if (responseData.features && responseData.features.length > 0) {
        const properties = responseData.features[0].properties;
        const totalDistance = properties.summary.distance; // Total distance in meters
        const totalDuration = properties.summary.duration; // Total duration in seconds
        const totalAscent = properties.ascent; // Total ascent in meters
        const totalDescent = properties.descent; // Total descent in meters
        var routeDetails = {
            distance: totalDistance, // in meters
            duration: totalDuration, // in seconds
            ascent: totalAscent,     // in meters
            descent: totalDescent    // in meters
        };

    }

    return {geojson: responseData, details: routeDetails};
}