(function () {
  'use strict';
  angular
  .module('omarApp')
  .controller('MapImageController', ['$scope', '$aside', '$state', '$stateParams',
    '$location', 'toastr', 'imageSpaceService', 'beNumberService', 'downloadService', MapImageController]);

  function MapImageController($scope, $aside, $state, $stateParams, $location, toastr,
     imageSpaceService, beNumberService, downloadService) {

    var vm = this;

    var imageSpaceObj = {};

    //used
    var bandNum;

    vm.archiveDownload = function( imageId ) {
      downloadService.downloadFiles( imageId );
    };

    function checkStateParams() {

      // Check to make sure that all of the $stateParams are defined.
      // If there are undefined params return an error.
      for (var i in $stateParams) {

        if ($stateParams[i] === undefined) {

          toastr.error('There was an issue loading the selected image into the map.',
              'A problem has occurred:', {
              positionClass: 'toast-bottom-left',
              closeButton: true,
              timeOut: 10000,
              extendedTimeOut: 5000,
              target: 'body'
            });

          return;

        }

      }

      // We can load the map, because all parameters
      // are present.
      loadMapImage();

    }

    checkStateParams();

    //Beginning - Band Selections Section

    $scope.bandValues = [];
    $scope.bandTypeValues = [
      { 'key': 0, 'value': 'Color' },
      { 'key': 1, 'value': 'Gray' }
    ];

    for ( bandNum = 0; bandNum < $stateParams.bands; bandNum++ ) {
      $scope.bandValues.push( { 'key': bandNum, 'value': bandNum } );
    }

    $scope.bandTypeItem = $scope.bandTypeValues[1];
    $scope.grayImageItem = $scope.bandValues[0];
    $scope.redImageItem = $scope.bandValues[0];
    $scope.greenImageItem = $scope.bandValues[1];
    $scope.blueImageItem = $scope.bandValues[2];

    $scope.showBands =  function( bandType ) {
      if ( bandType.toUpperCase() == 'COLOR' ) {
        $( '#rgb-image-space-bands' ).show();
        $( '#gray-image-space-bands' ).hide();

      }else {
        $( '#gray-image-space-bands' ).show();
        $( '#rgb-image-space-bands' ).hide();
      }

    };

    //END - Band Selection Section

    function loadMapImage() {

      imageSpaceObj = {
          filename: $stateParams.filename,
          entry: $stateParams.entry_id,
          imgWidth: $stateParams.width,
          imgHeight: $stateParams.height
        };

      // Pass our imageSpaceObj constructed from the URL
      // ($stateParams) into the imageSpaceService and load
      // the map.
      imageSpaceService.initImageSpaceMap(imageSpaceObj);

    }

    $scope.asideState = {
      open: false
    };

    $scope.openAside = function(position, backdrop) {
      $scope.asideState = {
        open: true,
        position: position
      };

      function postClose() {
        $scope.asideState.open = false;
      }

      $aside.open({
        templateUrl: AppO2.APP_CONFIG.serverURL + '/mapImage/aside.html',
        placement: position,
        size: 'sm',
        backdrop: false,
        controller: function ($scope, $uibModalInstance) {
          $scope.ok = function(e) {
            $uibModalInstance.close();
            e.stopPropagation();
          };

          $scope.cancel = function (e) {
            $uibModalInstance.dismiss();
            e.stopPropagation();
          };
        }
      }).result.then(postClose, postClose);
    }

  }

}());
