/* global L Papa */

/*
 * Script to display two tables from Google Sheets as point and geometry layers using Leaflet
 * The Sheets are then imported using PapaParse and overwrite the initially laded layers
 */

// PASTE YOUR URLs HERE
// these URLs come from Google Sheets 'shareable link' form
// the first is the geometry layer and the second the points

let geomURL ="";

let pointsURL ="https://docs.google.com/spreadsheets/d/e/2PACX-1vRBhaOEzhB8NoIV7GaHSxNoQ68PGH6TL88cla50r8eBFf7gq7rA5DQ5H1SQPeZvD26q_5e5OSBql75p/pub?gid=1001334901&single=true&output=csv";

window.addEventListener("DOMContentLoaded", init);

let map;
// let sidebar;
let panelID = "my-info-panel";

/*
 * init() is called when the page has loaded
 */
function init() {
  // Create a new Leaflet map centered on the continental US
  map = L.map("map").setView([23.78786, 90.35070], 13);

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

  // sidebar = L.control
  //   .sidebar({
  //     container: "sidebar",
  //     closeButton: true,
  //     position: "right",
  //   })
  //   .addTo(map);

  // let panelContent = {
  //   id: panelID,
  //   tab: "<i class='fa fa-bars active'></i>",
  //   pane: "<p id='sidebar-content'></p>",
  //   title: "<h2 id='sidebar-title'>Nothing selected</h2>",
  // };
  // sidebar.addPanel(panelContent);

  // map.on("click", function () {
  //   sidebar.close(panelID);
  // });

  // Use PapaParse to load data from Google Sheets
  // And call the respective functions to add those to the map.
  Papa.parse(geomURL, {
    download: true,
    header: true,
    complete: addGeoms,
  });
  Papa.parse(pointsURL, {
    download: true,
    header: true,
    complete: addPoints,
  });
}

/*
 * Expects a JSON representation of the table with properties columns
 * and a 'geometry' column that can be parsed by parseGeom()
 */
function addGeoms(data) {
  data = data.data;
  // Need to convert the PapaParse JSON into a GeoJSON
  // Start with an empty GeoJSON of type FeatureCollection
  // All the rows will be inserted into a single GeoJSON
  let fc = {
    type: "FeatureCollection",
    features: [],
  };

  for (let row in data) {
    // The Sheets data has a column 'include' that specifies if that row should be mapped
    if (data[row].include == "y") {
      let features = parseGeom(JSON.parse(data[row].geometry));
      features.forEach((el) => {
        el.properties = {
          name: data[row].name,
          description: data[row].description,
          image: data[row].image,
        };
        fc.features.push(el);
      });
    }
  }

  // The geometries are styled slightly differently on mouse hovers
  let geomStyle = { color: "#2ca25f", fillColor: "#99d8c9", weight: 2 };
  let geomHoverStyle = { color: "green", fillColor: "#2ca25f", weight: 3 };

  L.geoJSON(fc, {
    onEachFeature: function (feature, layer) {
      layer.on({
        mouseout: function (e) {
          e.target.setStyle(geomStyle);
        },
        mouseover: function (e) {
          e.target.setStyle(geomHoverStyle);
        },
        click: function (e) {
          // This zooms the map to the clicked geometry
          // Uncomment to enable
          // map.fitBounds(e.target.getBounds());

          // if this isn't added, then map.click is also fired!
          L.DomEvent.stopPropagation(e);

          // document.getElementById("sidebar-title").innerHTML =
          //   e.target.feature.properties.name;
          // document.getElementById("sidebar-content").innerHTML =
          //   e.target.feature.properties.description;
          // sidebar.open(panelID);
        },
      });
    },
    style: geomStyle,
  }).addTo(map);
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
      marker = L.circleMarker([data[row].lat, data[row].lon], {
        radius: markerRadius,
      });
    } else if (markerType == "circle") {
      marker = L.circle([data[row].lat, data[row].lon], {
        radius: markerRadius,
      });
    } else {
      marker = L.marker([data[row].lat, data[row].lon]);
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
