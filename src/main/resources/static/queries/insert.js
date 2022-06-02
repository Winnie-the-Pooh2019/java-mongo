// use pharmacy;

db.medicines.drop();
db.orders.drop();
db.clients.drop();
db.types.drop();
db.medicine_shipping.drop();
db.resource_shipping.drop();
db.resources.drop();


db.types.insertMany([
    {
        _id: new ObjectId(),
        name: "maz",
        attributes: {
            appliance: "on the skin",
            additionalTime: "2 days"
        },
        _class: "com.example.javamongo.data.entity.Type"
    },
    {

        _id: new ObjectId(),
        name: "krem",
        attributes: {
            appliance: "on the skin",
            color: "blue",
            weight: "mnogo"
        },
        _class: "com.example.javamongo.data.entity.Type"
    },
    {
        _id: new ObjectId(),
        name: "pills",
        _class: "com.example.javamongo.data.entity.Type"
    },
    {
        _id: new ObjectId(),
        name: "suspension",
        attributes: {
            "volume": "1 liter"
        },
        _class: "com.example.javamongo.data.entity.Type"
    },
    {
        _id: new ObjectId(),
        name: "drops",
        attributes: {
            doses: "mnogo"
        },
        _class: "com.example.javamongo.data.entity.Type"
    }
]);

let types = db.types.find({}, {_id: 1}).toArray();

db.resources.insertMany([
    {
        _id: new ObjectId(), name: "vodichka",
        "critical_amount": 100,
        "expiration": {
            "YEARS": 2
        },
        "price": 15.5,
        _class: "com.example.javamongo.data.entity.Resource"
    },
    {
        _id: new ObjectId(), name: "spirt",
        "critical_amount": 200,
        "expiration": {
            "MONTHS": 2
        },
        "price": 18.3,
        _class: "com.example.javamongo.data.entity.Resource"
    },
    {
        _id: new ObjectId(), name: "pure spirt",
        "critical_amount": 20,
        "expiration": {
            "MONTHS": 2
        },
        "price": 20.4,
        _class: "com.example.javamongo.data.entity.Resource"
    },
    {
        _id: new ObjectId(), name: "vodorod",
        "critical_amount": 500,
        "expiration": {
            "YEARS": 10
        },
        "price": 150,
        _class: "com.example.javamongo.data.entity.Resource"
    },
    {
        _id: new ObjectId(), name: "kislorod",
        "critical_amount": 500,
        "expiration": {
            "YEARS": 10
        },
        "price": 155,
        _class: "com.example.javamongo.data.entity.Resource"
    },
    {
        _id: new ObjectId(), name: "uglerod",
        "critical_amount": 600,
        "expiration": {
            "YEARS": 10
        },
        "price": 160,
        _class: "com.example.javamongo.data.entity.Resource"
    },
    {
        _id: new ObjectId(), name: "lipton ice tea",
        "critical_amount": 10,
        "expiration": {
            "MONTHS": 1,
            "DAYS": 8
        },
        "price": 10.7,
        _class: "com.example.javamongo.data.entity.Resource"
    }
]);

let resources = db.resources.find({}, {_id: 1}).toArray();
resources.forEach((r) => {
    console.log(r)
});
db.resource_shipping.find()

db.resource_shipping.insertMany([
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[1]._id },
        "amount": 15,
        "date_ordered": new Date("2018-04-28"),
        "date_picked": new Date("2018-05-30"),
        "status": "DELIVERED",
        "price": 12,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[0]._id },
        "amount": 32,
        "date_ordered": new Date("2020-04-28"),
        "date_picked": new Date("2020-05-21"),
        "status": "DELIVERED",
        "price": 13,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[0]._id },
        "amount": 14,
        "date_ordered": new Date("2022-02-28"),
        "status": "IN_TRANSIT",
        "price": 12,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[1]._id },
        "amount": 70,
        "date_ordered": new Date("2015-04-28"),
        "date_picked": new Date("2016-01-04"),
        "status": "DELIVERED",
        "price": 13,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[2]._id },
        "amount": 60,
        "date_ordered": new Date("2021-07-28"),
        "date_picked": new Date("2021-08-04"),
        "status": "DELIVERED",
        "price": 14,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[2]._id },
        "amount": 120,
        "date_ordered": new Date("2022-05-20"),
        "status": "IN_TRANSIT",
        "price": 14,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[2]._id },
        "amount": 14,
        "date_ordered": new Date("2020-06-28"),
        "date_picked": new Date("2020-06-30"),
        "status": "DELIVERED",
        "price": 18,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[2]._id },
        "amount": 20,
        "date_ordered": new Date("2021-02-08"),
        "date_picked": new Date("2021-03-10"),
        "status": "DELIVERED",
        "price": 17,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[2]._id },
        "amount": 22,
        "date_ordered": new Date("2022-03-14"),
        "status": "IN_TRANSIT",
        "price": 18,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[3]._id },
        "amount": 130,
        "date_ordered": new Date("2007-12-08"),
        "date_picked": new Date("2008-02-10"),
        "status": "DELIVERED",
        "price": 100,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[3]._id },
        "amount": 120,
        "date_ordered": new Date("2017-02-18"),
        "date_picked": new Date("2017-04-07"),
        "status": "DELIVERED",
        "price": 120,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[3]._id },
        "amount": 200,
        "date_ordered": new Date("2020-12-08"),
        "date_picked": new Date("2021-02-10"),
        "status": "DELIVERED",
        "price": 125,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[4]._id },
        "amount": 140,
        "date_ordered": new Date("2007-12-08"),
        "date_picked": new Date("2008-02-10"),
        "status": "DELIVERED",
        "price": 110,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[4]._id },
        "amount": 150,
        "date_ordered": new Date("2017-02-18"),
        "date_picked": new Date("2017-04-07"),
        "status": "DELIVERED",
        "price": 120,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[4]._id },
        "amount": 210,
        "date_ordered": new Date("2020-12-08"),
        "date_picked": new Date("2021-02-10"),
        "status": "DELIVERED",
        "price": 125,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[5]._id },
        "amount": 130,
        "date_ordered": new Date("2007-12-08"),
        "date_picked": new Date("2008-02-10"),
        "status": "DELIVERED",
        "price": 100,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[5]._id },
        "amount": 120,
        "date_ordered": new Date("2017-02-18"),
        "date_picked": new Date("2017-04-07"),
        "status": "DELIVERED",
        "price": 120,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[5]._id },
        "amount": 200,
        "date_ordered": new Date("2022-02-11"),
        "status": "IN_TRANSIT",
        "price": 150,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[6]._id },
        "amount": 5,
        "date_ordered": new Date("2022-02-18"),
        "date_picked": new Date("2022-02-27"),
        "status": "DELIVERED",
        "price": 7,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[6]._id },
        "amount": 10,
        "date_ordered": new Date("2022-04-14"),
        "date_picked": new Date("2017-04-27"),
        "status": "DELIVERED",
        "price": 8,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    },
    {
        _id: new ObjectId(),
        resource: {"$ref": "resources", "$id": resources[6]._id },
        "amount": 4,
        "date_ordered": new Date("2022-05-18"),
        "status": "IN_TRANSIT",
        "price": 7.5,
        _class: "com.example.javamongo.data.entity.ResourceShipping"
    }
]);

let rshippings = db.resource_shipping.find({}, {_id: 1}).toArray();

db.clients.insertMany([
    {
        _id: new ObjectId(),
        "last_name": "Romanov",
        "first_name": "Ivan",
        "patronymic": "Vasilyevich",
        "phone": "8(906)864-32-14",
        "address": "Petrov avenue",
        _class: "com.example.javamongo.data.entity.Client"
    },
    {
        _id: new ObjectId(),
        "last_name": "Sviridov",
        "first_name": "Oleg",
        "patronymic": "",
        "phone": "8(834)124-32-14",
        "address": "Lenina avenue",
        _class: "com.example.javamongo.data.entity.Client"
    },
    {
        _id: new ObjectId(),
        "last_name": "Olenev",
        "first_name": "Vasiliy",
        "patronymic": "Leonidovich",
        "phone": "8(827)864-21-23",
        "address": "Moskovskiy avenue",
        _class: "com.example.javamongo.data.entity.Client"
    },
    {
        _id: new ObjectId(),
        "last_name": "Yakovlev",
        "first_name": "Yakov",
        "patronymic": "Yakovlevich",
        "phone": "8(131)414-14-54",
        "address": "Yakovlevkoe avenue",
        _class: "com.example.javamongo.data.entity.Client"
    },
    {
        _id: new ObjectId(),
        "last_name": "Malinovich",
        "first_name": "Michael",
        "patronymic": "",
        "phone": "8(123)927-32-14",
        "address": "Malinovoe avenue",
        _class: "com.example.javamongo.data.entity.Client"
    },
    {
        _id: new ObjectId(),
        "last_name": "Yagodniy",
        "first_name": "Ivan",
        "patronymic": "Yagogka",
        "phone": "8(901)123-26-14",
        "address": "Yagodnoye avenue",
        _class: "com.example.javamongo.data.entity.Client"
    },
    {
        _id: new ObjectId(),
        "last_name": "Lubovliy",
        "first_name": "Luba",
        "patronymic": "Vasilyevich",
        "phone": "8(123)531-54-49",
        "address": "Lubovnoye avenue",
        _class: "com.example.javamongo.data.entity.Client"
    }
]);

let clients = db.clients.find({}, {_id: 1}).toArray();

db.medicines.insertMany([
    {
        _id: new ObjectId(),
        "name": "Spasmolgon",
        "critical_amount": 150,
        "expiration": {
            "YEARS": 2
        },
        "price": 133,
        type: {"$ref": "types", "$id": types[1]._id },
        "technology": {
            "description": "do something bla bla Spasmolgon",
            "prepare_time": {
                "DAYS": 2
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[0]._id },
                    "count": 1
                },
                {
                    resource: {"$ref": "resources", "$id": resources[1]._id },
                    "count": 7
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Cream soda",
        "critical_amount": 200,
        "expiration": {
            "YEARS": 2
        },
        "price": 98,
        type: {"$ref": "types", "$id": types[1]._id },
        "technology": {
            "description": "do something bla bla Cream soda",
            "prepare_time": {
                "HOURS": 3,
                "MINUTES": 20
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[2]._id },
                    "count": 2
                },
                {
                    resource: {"$ref": "resources", "$id": resources[4]._id },
                    "count": 4
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Glazogorin",
        "critical_amount": 150,
        "expiration": {
            "YEARS": 2
        },
        "price": 93,
        type: {"$ref": "types", "$id": types[2]._id },
        "technology": {
            "description": "do something bla bla Glazogorin",
            "prepare_time": {
                "DAYS": 1
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[5]._id },
                    "count": 5
                },
                {
                    resource: {"$ref": "resources", "$id": resources[0]._id },
                    "count": 1
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Analgetic",
        "critical_amount": 100,
        "expiration": {
            "YEARS": 2
        },
        "price": 133,
        type: {"$ref": "types", "$id": types[0]._id },
        "technology": {
            "description": "do something bla bla Analgetic",
            "prepare_time": {
                "DAYS": 6,
                "HOURS": 2
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[0]._id },
                    "count": 3
                },
                {
                    resource: {"$ref": "resources", "$id": resources[6]._id },
                    "count": 1
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Zodak",
        "critical_amount": 100,
        "expiration": {
            "YEARS": 2
        },
        "price": 63,
        type: {"$ref": "types", "$id": types[2]._id },
        "technology": {
            "description": "do something bla bla Zodak",
            "prepare_time": {
                "HOURS": 7
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[4]._id },
                    "count": 14
                },
                {
                    resource: {"$ref": "resources", "$id": resources[3]._id },
                    "count": 7
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Revit",
        "critical_amount": 50,
        "expiration": {
            "YEARS": 3
        },
        "price": 133,
        type: {"$ref": "types", "$id": types[1]._id },
        "technology": {
            "description": "do something bla bla Revit",
            "prepare_time": {
                "DAYS": 10
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[0]._id },
                    "count": 3
                },
                {
                    resource: {"$ref": "resources", "$id": resources[1]._id },
                    "count": 7
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Shadow fight",
        "critical_amount": 100,
        "expiration": {
            "YEARS": 1
        },
        "price": 13,
        type: {"$ref": "types", "$id": types[0]._id },
        "technology": {
            "description": "do something bla bla Shadow fight",
            "prepare_time": {
                "HOURS": 5
            },
            "resources": [
                {
                    resource: {"$ref": "resources", "$id": resources[3]._id },
                    "count": 4
                },
                {
                    resource: {"$ref": "resources", "$id": resources[1]._id },
                    "count": 3
                }
            ]
        },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Omeprazol",
        "critical_amount": 20,
        "expiration": {
            "YEARS": 1
        },
        "price": 500,
        type: {"$ref": "types", "$id": types[3]._id },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Drotaverin",
        "critical_amount": 50,
        "expiration": {
            "YEARS": 5
        },
        "price": 200,
        type: {"$ref": "types", "$id": types[3]._id },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Mebeverin",
        "critical_amount": 50,
        "expiration": {
            "YEARS": 7
        },
        "price": 190,
        type: {"$ref": "types", "$id": types[4]._id },
        _class: "com.example.javamongo.data.entity.Medicine"
    },
    {
        _id: new ObjectId(),
        "name": "Platifilin",
        "critical_amount": 100,
        "expiration": {
            "YEARS": 1
        },
        "price": 150,
        type: {"$ref": "types", "$id": types[4]._id },
        _class: "com.example.javamongo.data.entity.Medicine"
    }
]);

let medicines = db.medicines.find({}, {_id: 1}).toArray();

db.medicine_shipping.insertMany([
    {
        medicine: {"$ref": "medicines", "$id": medicines[0]._id },
        "amount": 12,
        "date_ordered": new Date("2022-04-28"),
        "status": "IN_TRANSIT",
        "price": 120,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[2]._id },
        "amount": 32,
        "date_ordered": new Date("2022-04-28"),
        "status": "IN_TRANSIT",
        "price": 85,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[7]._id },
        "amount": 14,
        "date_ordered": new Date("2022-04-28"),
        "status": "IN_TRANSIT",
        "price": 125,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[8]._id },
        "amount": 7,
        "date_ordered": new Date("2022-04-28"),
        "status": "IN_TRANSIT",
        "price": 120,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[7]._id },
        "amount": 20,
        "date_ordered": new Date("2022-04-28"),
        "status": "IN_TRANSIT",
        "price": 10,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[0]._id },
        "amount": 20,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 35,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[8]._id },
        "amount": 15,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 135,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[0]._id },
        "amount": 23,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 135,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[10]._id },
        "amount": 11,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 135,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[1]._id },
        "amount": 20,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 100,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[10]._id },
        "amount": 30,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 135,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[2]._id },
        "amount": 12,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 93,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[3]._id },
        "amount": 32,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 130,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[4]._id },
        "amount": 12,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-12-11"),
        "status": "DELIVERED",
        "price": 65,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[7]._id },
        "amount": 11,
        "date_ordered": new Date("2020-12-01"),
        "date_picked": new Date("2020-09-10"),
        "status": "DELIVERED",
        "price": 140,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[10]._id },
        "amount": 23,
        "date_ordered": new Date("2020-09-05"),
        "date_picked": new Date("2020-09-10"),
        "status": "DELIVERED",
        "price": 10,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[9]._id },
        "amount": 34,
        "date_ordered": new Date("2020-09-05"),
        "date_picked": new Date("2020-09-10"),
        "status": "DELIVERED",
        "price": 125,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[1]._id },
        "amount": 20,
        "date_ordered": new Date("2020-09-05"),
        "date_picked": new Date("2020-09-10"),
        "status": "DELIVERED",
        "price": 90,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[9]._id },
        "amount": 18,
        "date_ordered": new Date("2020-09-05"),
        "date_picked": new Date("2020-09-10"),
        "status": "DELIVERED",
        "price": 130,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    },
    {
        medicine: {"$ref": "medicines", "$id": medicines[2]._id },
        "amount": 19,
        "date_ordered": new Date("2020-09-05"),
        "date_picked": new Date("2020-09-10"),
        "status": "DELIVERED",
        "price": 95,
        _class: "com.example.javamongo.data.entity.MedicineShipping"
    }
]);

db.orders.insertMany([
    {
        "status": "PICKED",
        "date_picked": new Date("2020-09-11"),
        client: {"$ref": "clients", "$id": clients[1]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[0]._id },
                "amount": 2,
                "price": 132,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[1]._id },
                "amount": 4,
                "price": 99,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2008-06-02"),
        client: {"$ref": "clients", "$id": clients[2]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[2]._id },
                "amount": 5,
                "price": 90,
                "status": "IN_PROGRESS"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2020-09-11"),
        client: {"$ref": "clients", "$id": clients[2]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[3]._id },
                "amount": 2,
                "price": 100,
                "status": "NO_COMPONENTS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[4]._id },
                "amount": 12,
                "price": 60,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[5]._id },
                "amount": 9,
                "price": 100,
                "status": "IN_PROGRESS"
            }
        ]
    },
    {
        "status": "DONE",
        "date_picked": new Date("2019-07-17"),
        client: {"$ref": "clients", "$id": clients[0]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[6]._id },
                "amount": 4,
                "price": 15,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[0]._id },
                "amount": 5,
                "price": 132,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[1]._id },
                "amount": 6,
                "price": 99,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[7]._id },
                "amount": 2,
                "price": 505,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2008-06-02"),
        client: {"$ref": "clients", "$id": clients[3]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[2]._id },
                "amount": 1,
                "price": 90,
                "status": "IN_PROGRESS"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2008-06-02"),
        client: {"$ref": "clients", "$id": clients[5]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[3]._id },
                "amount": 3,
                "price": 100,
                "status": "NO_COMPONENTS"
            }
        ]
    },
    {
        "status": "PICKED",
        "date_picked": new Date("2020-09-11"),
        client: {"$ref": "clients", "$id": clients[6]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[4]._id },
                "amount": 5,
                "price": 60,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[5]._id },
                "amount": 8,
                "price": 100,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2019-07-17"),
        client: {"$ref": "clients", "$id": clients[3]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[6]._id },
                "amount": 1,
                "price": 15,
                "status": "NO_COMPONENTS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[0]._id },
                "amount": 2,
                "price": 132,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2008-06-02"),
        client: {"$ref": "clients", "$id": clients[6]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[1]._id },
                "amount": 3,
                "price": 99,
                "status": "IN_PROGRESS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[10]._id },
                "amount": 4,
                "price": 160,
                "status": "IN_PROGRESS"
            }
        ]
    },
    {
        "status": "PICKED",
        "date_picked": new Date("2020-09-11"),
        client: {"$ref": "clients", "$id": clients[5]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[3]._id },
                "amount": 4,
                "price": 100,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[4]._id },
                "amount": 5,
                "price": 60,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[0]._id },
                "amount": 6,
                "price": 100,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2019-07-17"),
        client: {"$ref": "clients", "$id": clients[0]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[6]._id },
                "amount": 12,
                "price": 15,
                "status": "IN_PROGRESS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[0]._id },
                "amount": 11,
                "price": 132,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[1]._id },
                "amount": 9,
                "price": 99,
                "status": "IN_PROGRESS"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2008-06-02"),
        client: {"$ref": "clients", "$id": clients[4]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[2]._id },
                "amount": 8,
                "price": 90,
                "status": "IN_PROGRESS"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2008-06-02"),
        client: {"$ref": "clients", "$id": clients[1]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[3]._id },
                "amount": 3,
                "price": 100,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[7]._id },
                "amount": 4,
                "price": 520,
                "status": "IN_PROGRESS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[9]._id },
                "amount": 1,
                "price": 195,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2022-02-14"),
        client: {"$ref": "clients", "$id": clients[1]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[4]._id },
                "amount": 4,
                "price": 60,
                "status": "NO_COMPONENTS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[5]._id },
                "amount": 1,
                "price": 100,
                "status": "NO_COMPONENTS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[6]._id },
                "amount": 4,
                "price": 15,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[0]._id },
                "amount": 5,
                "price": 132,
                "status": "IN_PROGRESS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[1]._id },
                "amount": 6,
                "price": 99,
                "status": "IN_PROGRESS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[8]._id },
                "amount": 1,
                "price": 220,
                "status": "OK"
            }
        ]
    },
    {
        "status": "IN_PROGRESS",
        "date_picked": new Date("2022-01-20"),
        client: {"$ref": "clients", "$id": clients[2]._id },
        "medicines": [
            {
                medicine: {"$ref": "medicines", "$id": medicines[2]._id },
                "amount": 4,
                "price": 90,
                "status": "IN_PROGRESS"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[9]._id },
                "amount": 2,
                "price": 200,
                "status": "OK"
            },
            {
                medicine: {"$ref": "medicines", "$id": medicines[10]._id },
                "amount": 1,
                "price": 150,
                "status": "IN_PROGRESS"
            }
        ]
    }
]);