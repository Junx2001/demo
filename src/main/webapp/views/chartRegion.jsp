<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.10.0/css/ol.css" type="text/css">
<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.10.0/build/ol.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
<style>
    .map {
        height: 600px;
        width: 100%;
    }
</style>
<%-- <script src="${baseURL}/views/assets/v6.10.0-dist/ol.js"></script> --%>
<title></title>
<h2>My Map</h2>
<div class="overflow-auto">
    <div id="map" class="map"><div id="popup"></div>

    </div>
</div>
<!--  <div id="popup" class="ol-popup" >
    <a href="#" id="popup-closer" class="ol-popup-closer"></a>
    <div id="popup-content" class="col-lg-3"></div>
</div> -->
</div>

<script>
    window.onload = init;
    function init(event) {
        var map = new ol.Map({
            target: 'map',
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.OSM()
                })
            ],
            view: new ol.View({
                center: ol.proj.fromLonLat([47.50792, -18.8792]),
                zoom: 6
            })
        });

        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');


        var listeLayer = [];
        var listeSignalement = [];
        var cordLayer = [];
        var i = 0;
        //console.log("${signalements}");
	let temp;
    <c:forEach  items="${signalements}" var ="signalement">
    temp = "<div class=\"card\"> \
        <img src=\"${baseURL}/views/assets/img/imgCloud/${signalement.nomImage}\" width=\"50\" class=\"card-img-top\" alt=\"...\"> \
        <input type=\"hidden\" id=\"url\" value=\"${baseURL}\"> \
        <div class=\"card-body\"> \
     <h5 class=\"card-title\">Signalement <span id=\"idSignalement\">${signalement.idSignalement}</span></h5> \
     <p class=\"card-text\">Description: ${signalement.description}</p> \
     <p class=\"card-text\">Date: ${signalement.dateSignalement}</p> \
     <hr> \
     <p class=\"card-text\">Longitude: ${signalement.longitude}</p> \
     <p class=\"card-text\">Latitude: ${signalement.latitude}</p> \
     <hr> \
     <select name=\"idRegion\" id=\"idRegion\" class=\"form-select\">";

	<c:forEach  items="${regions}" var ="region">
	temp += "<option value=\"${region.idRegion}\">${region.nom}</option>";
	</c:forEach>
	
	temp += "</select> \
	     <button  type=\"button\" class=\"btn btn-info\" id=\"bouton\">Affecter</button> \
	     </div>";
    console.log(temp);
        listeSignalement[i] = "${signalement}";
        cordLayer[i] = event.coordinate;
        var coord = ol.proj.fromLonLat([${signalement.longitude}, ${signalement.latitude}]);
        listeLayer[i] = new ol.layer.Vector({
            source: new ol.source.Vector({
                projection: 'EPSG:4326',
                features: [new ol.Feature({
                    geometry : new ol.geom.Point(coord),
                    contenu: temp,
                    })]
            }),

            style: [
            	new ol.style.Style({
                    image: new ol.style.Icon({
                      anchor: [0.5, 0.5],
                      anchorXUnits: 'fraction',
                      anchorYUnits: 'pixels',
                      src: '${baseURL}/views/assets/img/icon.png',
                      scale:0.08,
                    }),
                  })
             ],
            // style: [
            //   new ol.style.Style({
            //     stroke: new ol.style.Stroke({
            //       color: 'blue',
            //       width: 3
            //     }),
            //     fill: new ol.style.Fill({
            //       color: 'rgba(0, 0, 255, 0.1)'
            //     })
            //   })
            // ]
        });

        map.addLayer(listeLayer[i]);

        i++;
    </c:forEach>

    map.on('pointermove', function (e) {
    	  const pixel = map.getEventPixel(e.originalEvent);
    	  const hit = map.hasFeatureAtPixel(pixel);
    	  map.getTarget().style.cursor = hit ? 'pointer' : '';
    	});

    const element = document.getElementById('popup');

    const popup = new ol.Overlay({
      element: element,
      positioning: 'bottom-center',
      stopEvent: false,
    });
    map.addOverlay(popup);

   
    map.on('click', function (evt) {
    const feature = map.forEachFeatureAtPixel(evt.pixel, function (feature) {
      return feature;
    });
    if (feature) {
      popup.setPosition(evt.coordinate);
      var newScript = document.createElement("script");
      var inlineScript = document.createTextNode("$('#bouton').click(function () {\
                      var baseUrl = $('#url').val();\
                      var sign = $('#idSignalement').html();\
                      var region = $('#idRegion').val();\
                      setTimeout(() => {location.reload();}, 3000);\
                      $.ajax({\
                          url: baseUrl + '/back/signalement/' + sign,\
                          method: 'put',\
                          data: {region: region},\
                          dataType: 'json',\
                          success: function (response) {\
                              console.log(\"put method successfully done\");\
                              console.log(response);\
                          }\
                      });\
                  });");
      newScript.appendChild(inlineScript);
      container.appendChild(newScript);
      $(element).popover({
        placement: 'right',
        html: true,
        content: feature.get('contenu'),
      });
      $(element).popover('show');
    } else {
      $(element).popover('dispose');
    }
  });

    map.on('movestart', function () {
    	  $(element).popover('dispose');
    	});


        /*var marker = new ol.layer.Vector({
         source: new ol.source.Vector({
         features: [
         new ol.Feature({
         geometry: new ol.geom.Point(ol.proj.fromLonLat([47.50792, -18.8792]))
         })
         ]
         })
         });*/
        //map.addLayer(layer);

        //map.addLayer(marker);
        /*var overlay = new ol.Overlay({
            element: content,
            autoPan: false,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(overlay);

        closer.onclick = function () {
            overlay.setPosition(undefined);
            closer.blur();
            return false;
        };*/
        //console.log(listeLayer[0].getSource().getFeatures());
        /*map.getView().on('change:resolution', function (evt) {

            var resolution = evt.target.get(evt.key),
                    resolution_constant = 40075016.68557849,
                    tile_pixel = 256;

            var result_resol_const_tile_px = resolution_constant / tile_pixel / resolution;

            var currentZoom = Math.log(result_resol_const_tile_px) / Math.log(2);
            console.info(currentZoom, resolution);

            //now find your features and apply radius
            if (resolution > 15000) {
                for (let l = 0; l < listeLayer.length; l++) {
                    listeLayer[l].getSource().forEachFeature(function (feature) {
                        console.log(feature);
                        //feature.getStyle().getGeometry().setRadius(100000);
                    });
                    //feature.getStyle().getGeometry().setRadius(100000);
                }
            } else if (resolution <= 15000 && resolution > 6000) {
                for (let l = 0; l < listeLayer.length; l++) {
                    listeLayer[l].getSource().forEachFeature(function (feature) {
                        feature.getStyle().getGeometry().setRadius(30000);
                    });
                    //feature.getStyle().getGeometry().setRadius(100000);
                }
            }
        });*/

        /*map.on('singleclick', function (event) {

            if (map.hasFeatureAtPixel(event.pixel) === true) {
                var feature = map.forEachFeatureAtPixel(event.pixel, function (feature, layer) {
                    var coordinate = event.coordinate;
                    for (let j = 0; j < listeLayer.length; j++) {
                        if (layer === listeLayer[j]) {

                            var k = 0;
    <c:forEach  items="${signalements}" var ="signalement">

                            if (k == j) {
                                var lfeture = map.getFeaturesAtPixel(event.pixel);
                                let temp = "<div class=\"card\"> \
                                        <img src=\"${baseURL}/views/assets/img/imgCloud/${signalement.nomImage}\" width=\"50\" class=\"card-img-top\" alt=\"...\"> \
                                        <input type=\"hidden\" id=\"url\" value=\"${baseURL}\"> \
                                        <div class=\"card-body\"> \
                                     <h5 class=\"card-title\">Signalement <span id=\"idSignalement\">${signalement.idSignalement}</span></h5> \
                                     <p class=\"card-text\">Description: ${signalement.description}</p> \
                                     <p class=\"card-text\">Date: ${signalement.dateSignalement}</p> \
                                     <hr> \
                                     <p class=\"card-text\">Longitude: ${signalement.longitude}</p> \
                                     <p class=\"card-text\">Latitude: ${signalement.latitude}</p> \
                                     <hr> \
                                     <select name=\"idRegion\" id=\"idRegion\" class=\"form-select\">";

        <c:forEach  items="${regions}" var ="region">
                                temp += "<option value=\"${region.idRegion}\">${region.nom}</option>";
        </c:forEach>

                                temp += "</select> \
                                     <button  type=\"button\" class=\"btn btn-info\" id=\"bouton\">Affecter</button> \
                                     </div>";
                                content.innerHTML = temp;
                                overlay.setPosition(coordinate);

                                var newScript = document.createElement("script");
                                var inlineScript = document.createTextNode("$('#bouton').click(function () {\
                                                var baseUrl = $('#url').val();\
                                                var sign = $('#idSignalement').html();\
                                                var region = $('#idRegion').val();\
                                                setTimeout(() => {location.reload();}, 3000);\
                                                $.ajax({\
                                                    url: baseUrl + '/back/signalement/' + sign,\
                                                    method: 'put',\
                                                    data: {region: region},\
                                                    dataType: 'json',\
                                                    success: function (response) {\
                                                        console.log(\"put method successfully done\");\
                                                        console.log(response);\
                                                    }\
                                                });\
                                            });");
                                newScript.appendChild(inlineScript);
                                container.appendChild(newScript);
                            }
                            k++;

    </c:forEach>

                        }
                    }
                });



            } else {
                overlay.setPosition(undefined);
                closer.blur();
            }
        });*/

    }
</script>

