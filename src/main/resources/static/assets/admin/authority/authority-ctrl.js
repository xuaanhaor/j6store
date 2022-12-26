app.controller("authority-ctrl", function ($scope, $http, $location) {
   $scope.roles=[];
   $scope.admins=[];
   $scope.authorities=[];

   $scope.initialize= function (){
      // load all roles
      $http.get("/rest/roles").then(resp =>{
         $scope.roles=resp.data;
      })

      // load staffs and directors(Admin)
      $http.get("/rest/accounts?admin=true").then(resp =>{
         $scope.admins=resp.data;
      })

      // load authorities off staffs and directors
      $http.get("/rest/authorities?admin=true").then(resp =>{
         $scope.authorities = resp.data;
      }).catch(error =>{
         console.log(error)
         // $location.path("/unauthorized")
      })
   }
   $scope.authority_of= function (acc,role){
      if ($scope.authorities){
         return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
      }
   }

   $scope.authority_changed= function (acc,role){
      var authority =$scope.authority_of(acc,role);
      if (authority){// da cap quuyen => thu hoi quyen(xoa)
         $scope.revoke_authority(authority);
      }else {// chua duoc cap quyen => cap quyen(them moi)
         authority={account:acc, role: role};
         $scope.grant_authority(authority);
      }
   }

   //Them moi quyen su dung
   $scope.grant_authority = function (authority){
      $http.post(`/rest/authorities`,authority).then(resp =>{
         $scope.authorities.push(resp.data)
         alert("Cap quyen su dung thanh cong");
      }).catch(error =>{
         alert("cap quyen su dung that bai");
         console.log(error);
      })
   }
   // Thu hoi quyen su dung
   $scope.revoke_authority = function (authority){
      $http.delete(`/rest/authorities/${authority.id}`).then(resp =>{
         var index = $scope.authorities.findIndex(a=>a.id==authority.id);
         $scope.authorities.slice(index,1)
         alert("thu hoi quyen su dung thanh cong");
      }).catch(error =>{
         alert("thu hoi quyen su dung that bai");
         console.log(error);
      })
   }
   $scope.initialize();
});