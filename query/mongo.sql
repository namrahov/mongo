db.student.find();

db.student.find().count();

db.student.find().limit(1);

db.student.find({firstName:'Memmed'});

db.student.find({totalSpentInBooks : {$in: [1000,6000]} });

db.student.find( {$or: [{firstName: 'Memmed'}, {lastName: 'Hesen'}] } );

db.student.find().sort({totalSpentInBooks:+1}).limit(1)  //asc order
db.student.find().sort({totalSpentInBooks:-1}).limit(1)  //desc order

db.student.aggregate([
    {"$group" : {_id:"$firstName", count:{$sum:1}}}     //SELECT firstName, COUNT(*) FROM student GROUP BY firstName
])

db.student.aggregate([
    {"$group" : {_id:{source:"$firstName",lastName:"$lastName"}, count:{$sum:1}}} //SELECT firstName, lastName, COUNT(*) FROM student GROUP BY firstName, lastName
])

db.student.find().max({totalSpentInBooks:+1})

db.student.insertOne(
    {
        "firstName":"Memmed",
        "lastName":"Eliyev",
        "email":"deliii@gmail.com",
        "gender":"MALE",
        "address":{
            "country":"Azerbaijan",
            "city":"Baku",
            "postCode":"AZ5011"
        },
        "favouriteSubject":["Geography"],
        "totalSpentInBooks": 5555
    }
    )