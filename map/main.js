/* global L Papa */

/*
 * Script to display two tables from Google Sheets as point and geometry layers using Leaflet
 * The Sheets are then imported using PapaParse and overwrite the initially laded layers
 */

// PASTE YOUR URLs HERE
// these URLs come from Google Sheets 'shareable link' form
// the first is the geometry layer and the second the points


let pointsURL ="https://docs.google.com/spreadsheets/d/e/2PACX-1vRBhaOEzhB8NoIV7GaHSxNoQ68PGH6TL88cla50r8eBFf7gq7rA5DQ5H1SQPeZvD26q_5e5OSBql75p/pub?gid=0&single=true&output=csv";

window.addEventListener("DOMContentLoaded", init);

let map;
// let sidebar;
let panelID = "my-info-panel";

/*
 * init() is called when the page has loaded
 */
function init() {
  // Create a new Leaflet map centered on the continental US
  map = L.map("map").setView([23.78786, 90.35070], 6);

  // This is the Carto Positron basemap
  L.tileLayer(
    'https://mt1.google.com/vt/lyrs=y&x={x}&y={y}&z={z}',
    {opacity: 1.0,
      attribution:
        "&copy; <a href='https://www.google.com/maps/' target='_blank'>Google Hyderabad</a>",
      subdomains: "abcd",
      maxZoom: 19,
    }
  ).addTo(map);


  Papa.parse(pointsURL, {
    download: true,
    header: true,
    complete: addPoints,
  });
}



/*
 * addPoints is a bit simpler, as no GeoJSON is needed for the points
 */



function addPoints(data) {
  data = data.data; 
  let pointGroupLayer = L.layerGroup().addTo(map);

  // Choose marker type. Options are:
  // (these are case-sensitive, defaults to marker!)
  // marker: standard point with an icon
  // circleMarker: a circle with a radius set in pixels
  // circle: a circle with a radius set in meters
  let markerType = "marker";

  // Marker radius
  // Wil be in pixels for circleMarker, metres for circle
  // Ignore for point
  let markerRadius = 100;

  for (let row = 0; row < data.length; row++) {
    let marker;
    if (markerType == "circleMarker") {
      marker = L.circleMarker([data[row].lat, data[row].long], {
        radius: markerRadius,
      });
    } else if (markerType == "circle") {
      marker = L.circle([data[row].lat, data[row].long], {
        radius: markerRadius,
      });
    } else {
      marker = L.marker([data[row].lat, data[row].long]);
    }
    marker.addTo(pointGroupLayer);

    // let markers = L.markerClusterGroup();
    //     markers.addLayer(L.marker(getRandomLatLng(map)));
    //     map.addLayer(markers);

    // UNCOMMENT THIS LINE TO USE POPUPS
    marker.bindPopup('<h3> Model: ' + data[row].model + '</h3> Brand: ' + data[row].brand + '');

    // COMMENT THE NEXT GROUP OF LINES TO DISABLE SIDEBAR FOR THE MARKERS
    // marker.feature = {
    //   properties: {
    //     name: data[row].name,
    //     description: data[row].description,
    //   },
    // };
    // marker.on({
    //   click: function (e) {
    //     L.DomEvent.stopPropagation(e);
    //     document.getElementById("sidebar-title").innerHTML =
    //       e.target.feature.properties.name;
    //     document.getElementById("sidebar-content").innerHTML =
    //       e.target.feature.properties.description;
    //     sidebar.open(panelID);
    //   },
    // });
    // COMMENT UNTIL HERE TO DISABLE SIDEBAR FOR THE MARKERS

    // AwesomeMarkers is used to create fancier icons
    let icon = L.AwesomeMarkers.icon({
      icon: "info-circle",
      iconColor: "white",
      markerColor: data[row].color,
      prefix: "fa",
      extraClasses: "fa-rotate-0",
    });
    if (!markerType.includes("circle")) {
      marker.setIcon(icon);
    }
  }
}

/*
 * Accepts any GeoJSON-ish object and returns an Array of
 * GeoJSON Features. Attempts to guess the geometry type
 * when a bare coordinates Array is supplied.
 */
function parseGeom(gj) {
  // FeatureCollection
  if (gj.type == "FeatureCollection") {
    return gj.features;
  }

  // Feature
  else if (gj.type == "Feature") {
    return [gj];
  }

  // Geometry
  else if ("type" in gj) {
    return [{ type: "Feature", geometry: gj }];
  }

  // Coordinates
  else {
    let type;
    if (typeof gj[0] == "number") {
      type = "Point";
    } else if (typeof gj[0][0] == "number") {
      type = "LineString";
    } else if (typeof gj[0][0][0] == "number") {
      type = "Polygon";
    } else {
      type = "MultiPolygon";
    }
    return [{ type: "Feature", geometry: { type: type, coordinates: gj } }];
  }
}
