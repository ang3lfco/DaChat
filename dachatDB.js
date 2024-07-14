use dachat;
db.createCollection("users");
db.createCollection("chats");
db.createCollection("messages");

db.users.insertMany([
{
   _id: ObjectId(),
   name: "",
   phone: "",
   password: "",
   address: {
       street: "",
       number: "",
       zipcode: ""
   },
   birthdate: new Date(''),
   gender: "",
   profile: ""
},
{
    _id: ObjectId(),
   name: "",
   phone: "",
   password: "",
   address: {
       street: "",
       number: "",
       zipcode: ""
   },
   birthdate: new Date(''),
   gender: "",
   profile: ""
}]);

db.chats.insertMany([{
    _id: ObjectId(),
    name: "",
    miniature: "",
    participants: ["",""] 
}]);

db.messages.insertMany([{
   _id: ObjectId(),
   id_chat: ObjectId(""),
   id_sender: "",
   text: "",
   timestamp: new Date()
}]);

db.users.find({});
db.chats.find({});
db.messages.find({});