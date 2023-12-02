async function fetchHikingRoute(waypoints) {
    const apiKey = '5b3ce3597851110001cf62485ca8ad3559e045a288a2bf868a1afd96';
    const url = 'https://api.openrouteservice.org/v2/directions/foot-hiking/geojson';

    console.log("waypoints in fetch: ", waypoints);
    const formattedWaypoints = waypoints.map(wp => [wp.lng, wp.lat]);

    const body = {
        coordinates: formattedWaypoints,
        format: 'geojson'
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
        const segment = responseData.features[0].properties.segments[0];
        const totalDistance = segment.distance; // Total distance in meters
        const totalDuration = segment.duration; // Total duration in seconds
        const totalAscent = segment.ascent; // Total ascent in meters
        const totalDescent = segment.descent; // Total descent in meters
        var routeDetails = {
            distance: segment.distance, // in meters
            duration: segment.duration, // in seconds
            ascent: segment.ascent,     // in meters
            descent: segment.descent    // in meters
        };

        // Display or return these values as needed
        console.log(`Distance: ${totalDistance} meters, Duration: ${totalDuration} seconds, Ascent: ${totalAscent} meters, Descent: ${totalDescent} meters`);
    }

    console.log("Responsedata!");
    console.log(responseData);

    return await {geojson: responseData, details: routeDetails};
}