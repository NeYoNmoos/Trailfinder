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

    console.log(response);

    if (!response.ok) {
        const message = `An error occurred: ${response.statusText}`;
        throw new Error(message);
    }

    return await response.json();
}