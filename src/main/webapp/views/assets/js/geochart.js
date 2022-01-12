window.onload = init;
function init(){
    var map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        view: new ol.View({
          center: ol.proj.fromLonLat([ 47.50792, -18.8792]),
          zoom: 6
        })
      });


    var coor = ol.proj.fromLonLat([47.50792, -18.8792]);
    
    

    var layer = new ol.layer.Vector({
        source: new ol.source.Vector({
          projection: 'EPSG:4326',
          features: [new ol.Feature(new ol.geom.Circle(coor, 40000))]
        }),
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
    
    console.log("${signalement}");

    /*var marker = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: [
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([47.50792, -18.8792]))
                })
            ]
        })
    });*/
    map.addLayer(layer);

    //map.addLayer(marker);

    var container = document.getElementById('popup');
    var content = document.getElementById('popup-content');
    var closer = document.getElementById('popup-closer');
   
    var overlay = new ol.Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlay);
   
    closer.onclick = function() {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
    };

    map.on('singleclick', function (event) {
        if (map.hasFeatureAtPixel(event.pixel) === true) {
            var coordinate = event.coordinate;
   
            content.innerHTML = '<b>Hello world!</b><br />I am a popup.';
            overlay.setPosition(coordinate);
        } else {
            overlay.setPosition(undefined);
            closer.blur();
        }
    });
   
}