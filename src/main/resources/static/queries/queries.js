// use pharmacy
db.orders.find();
// 1
let count = db.orders.aggregate(
    {
        $match: {
            $and: [
                {date_picked: {$lt: new Date()}},
                {status: {$eq: "DONE"}}
            ]
        }
    },
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {$group: {_id: "$client"}},
    {$count: "clients"}
).toArray()[0].clients;

db.orders.aggregate(
    {
        $match: {
            $and: [
                {date_picked: {$lt: new Date()}},
                {status: {$eq: "DONE"}}
            ]
        }
    },
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}}},
    {
        $group: {
            _id: {
                id: "$client._id",
                last_name: "$client.last_name",
                first_name: "$client.first_name"
            }, count: {$count: {}}
        }
    },
    {$project: {_id: 0, id: "$_id.id", last_name: "$_id.last_name", first_name: "$_id.first_name", status: 1, count: 1}}
);

// 2.1
db.orders.aggregate(
    {$match: {"status": {$eq: "IN_PROGRESS"}}},
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}}},
    {
        $group: {
            _id: {
                id: "$client._id",
                last_name: "$client.last_name",
                first_name: "$client.first_name"
            }
        }
    },
    {$project: {_id: 0, id: "$_id.id", last_name: "$_id.last_name", first_name: "$_id.first_name", status: 1}}
);

// 2.2
db.orders.aggregate(
    {$match: {"status": {$eq: "IN_PROGRESS"}}},
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {$unwind: "$medicines"},
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "types",
            localField: "medicine.type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {
        $match: {"type.name": {$eq: "krem"}}
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}}},
    {
        $group: {
            _id: {
                id: "$client._id",
                last_name: "$client.last_name",
                first_name: "$client.first_name"
            }
        }
    },
    {$project: {_id: 0, id: "$_id.id", last_name: "$_id.last_name", first_name: "$_id.first_name", status: 1}}
);

// 3.1
db.orders.aggregate(
    {
        $unwind: {path: "$medicines"}
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {$project: {medicineName: {$arrayElemAt: ["$medicine.name", 0]}, medicineAmount: "$medicines.amount"}},
    {$group: {_id: "$medicineName", count: {$sum: "$medicineAmount"}}},
    {$sort: {count: -1}},
    {$limit: 10}
);

// 3.2
db.orders.aggregate(
    {
        $unwind: {path: "$medicines"}
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "types",
            localField: "medicine.type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {
        $match: {"type.name": {$eq: "krem"}}
    },
    {$project: {medicineName: {$arrayElemAt: ["$medicine.name", 0]}, medicineAmount: "$medicines.amount"}},
    {$group: {_id: "$medicineName", count: {$sum: "$medicineAmount"}}},
    {$sort: {count: -1}},
    {$limit: 10}
);

// 4.1
db.orders.aggregate(
    {
        $unwind: {path: "$medicines"}
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {
        $match: {
            $and: [
                {date_picked: {$gte: new Date("2002-01-01")},},
                {date_picked: {$lte: new Date("2021-01-01")}},
                {"medicine.name": {$eq: "Spasmolgon"}}
            ]
        },
    },
    {$group: {_id: "$client"}},
    {$count: "clients"}
);
// console.log(count.count);
db.orders.aggregate(
    {
        $unwind: {path: "$medicines"}
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {
        $match: {
            $and: [
                {date_picked: {$gte: new Date("2002-01-01")},},
                {date_picked: {$lte: new Date("2021-01-01")}},
                {"medicine.name": {$eq: "Spasmolgon"}}
            ]
        },
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}, people: []}},
    {$group: {_id: "$client", count: {$count: {}}}},
    {$project: {_id: 0, id: "$_id.id", last_name: "$_id.last_name", first_name: "$_id.first_name", count: 1}}
);

// 4.2
db.orders.aggregate(
    {
        $unwind: {path: "$medicines"}
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {
        $lookup: {
            from: "types",
            localField: "medicine.type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {
        $match: {
            $and: [
                {date_picked: {$gte: new Date("2002-01-01")},},
                {date_picked: {$lte: new Date("2021-01-01")}},
                {
                    "type.name": {
                        $in: ["maz", "krem"]
                    }
                }
            ]
        },
    },
    {$group: {_id: "$client"}},
    {$count: "clients"}
);
db.orders.aggregate(
    {
        $unwind: {path: "$medicines"}
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {
        $lookup: {
            from: "types",
            localField: "medicine.type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {
        $match: {
            $and: [
                {date_picked: {$gte: new Date("2002-01-01")},},
                {date_picked: {$lte: new Date("2021-01-01")}},
                {
                    "type.name": {
                        $in: ["maz", "krem"]
                    }
                }
            ]
        },
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}, people: []}},
    {$group: {_id: "$client", count: {$count: {}}}},
    {$project: {_id: 0, id: "$_id.id", last_name: "$_id.last_name", first_name: "$_id.first_name", count: 1}}
);

// 5

db.medicines.aggregate(
    {
        $lookup: {
            from: "medicine_shipping",
            localField: "_id",
            foreignField: "medicine.$id",
            as: "shipping"
        }
    },
    {$addFields: {amount: {$sum: "$shipping.amount"}}},
    {$addFields: {cmpt: {$cmp: ["$amount", "$critical_amount"]}}},
    {
        $match: {
            $or: [
                {"shipping.status": "DELIVERED"},
                {"shipping": {$size: 0}}
            ]
        }
    },
    {$match: {cmpt: {$eq: -1}}},
    {$project: {name: 1, amount: 1, critical_amount: 1}}
)

// 6
db.orders.aggregate(
    {$match: {status: "IN_PROGRESS"}},
    {$count: "orders in progress"}
);

db.orders.aggregate(
    {$match: {status: "IN_PROGRESS"}},
    {
        $group: {
            _id: {
                id: "$_id",
                status: "$status",
                date_picked: "$date_picked",
                client_id: "$client.$id"
            }
        }
    },
    {
        $project: {
            id: "$_id.id", status: "$_id.status", date_picked: "$_id.date_picked",
            client_id: "$_id.client_id", _id: 0
        }
    }
);

// 7 ???
// число
db.orders.aggregate(
    {$match: {status: "IN_PROGRESS"}},
    {$unwind: {path: "$medicines"}},
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {$group: {_id: "$medicine", count: {$count: {}}}},
    {$count: "medicines for orders"}
);

// первый вариант
db.orders.aggregate(
    {$match: {status: "IN_PROGRESS"}},
    {$unwind: {path: "$medicines"}},
    {$addFields: {amount: "$medicines.amount"}},
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {$unwind: {path: "$medicine"}},
    {$group: {_id: "$medicine", count: {$sum: "$amount"}}},
    {$project: {medicineName: "$_id.name", medicineId: "$_id._id", _id: 0, count: 1}}
);

// 8.1
db.medicines.aggregate(
    {
        $lookup: {
            from: "types",
            localField: "type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {$unwind: {path: "$technology.resources"}},
    {
        $lookup: {
            from: "resources",
            localField: "technology.resources.resource.$id",
            foreignField: "_id",
            as: "resource"
        }
    },
    {$unwind: {path: "$resource"}},
    {
        $match: {
            $and: [
                {technology: {$ne: null}},
                {"type.name": {$in: ["maz", "krem"]}}
            ]
        }
    },
    {
        $project: {
            _id: 0,
            name: 1,
            description: "$technology.description",
            resource: 1,
            prepareTime: "$technology.prepare_time",
            resourceAmount: "$technology.resources.count"
        }
    }
);
db.orders.aggregate(
    {$unwind: {path: "$medicines"}},
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "types",
            localField: "medicine.type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {$unwind: {path: "$type"}},
    {
        $lookup: {
            from: "resources",
            localField: "medicine.technology.resources._id",
            foreignField: "_id",
            as: "resource"
        }
    },
    {$addFields: {medicine: {$arrayElemAt: ["$medicine", 0]}}},
    {$group: {_id: {medicine: "$medicine", type: "$type.name"}}},
    {
        $match: {
            $and: [
                {"_id.medicine.technology": {$ne: null}},
                {"_id.type": {$in: ["maz", "krem"]}}
            ]
        }
    },
    {
        $project: {
            medicine: "$_id.medicine.name",
            typeName: "$_id.type",
            description: "$_id.medicine.technology.description",
            prepare_time: "$_id.medicine.technology.prepareTime",
            resourcesName: "$_id.medicine.technology.resources",
            _id: 0
        }
    }
);

// 8.2
db.medicines.aggregate(
    {
        $match: {
            $and: [
                {technology: {$ne: null}},
                {name: {$in: ["Spasmolgon"]}}
            ]
        }
    },
    {$project: {_id: 0, name: 1, technology: 1}}
)

db.orders.aggregate(
    // {$match: {status: "IN_PROGRESS"}},
    {$unwind: {path: "$medicines"}},
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {$addFields: {medicine: {$arrayElemAt: ["$medicine", 0]}}},
    {$group: {_id: "$medicine"}},
    {
        $match: {
            $and: [
                {"_id.technology": {$ne: null}},
                {"_id.name": {$in: ["Spasmolgon"]}}
            ]
        }
    },
    {
        $project: {
            medicine: "$_id.name",
            description: "$_id.technology.description",
            prepare_time: "$_id.technology.prepareTime",
            resources: "$_id.technology.resources",
            _id: 0
        }
    }
);

// 8.3
db.orders.aggregate(
    {$match: {status: "IN_PROGRESS"}},
    {$unwind: {path: "$medicines"}},
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {$addFields: {medicine: {$arrayElemAt: ["$medicine", 0]}}},
    {$group: {_id: "$medicine"}},
    {
        $match: {
            $and: [
                {"_id.technology": {$ne: null}}
            ]
        }
    },
    {
        $project: {
            medicine: "$_id.name",
            description: "$_id.technology.description",
            prepare_time: "$_id.technology.prepareTime",
            resources: "$_id.technology.resources",
            _id: 0
        }
    }
);

// 9 ??? ценах что???
db.medicines.aggregate(
    {$unwind: {path: "$technology.resources"}},
    {
        $lookup: {
            from: "resources",
            localField: "technology.resources.resource.$id",
            foreignField: "_id",
            as: "resource"
        }
    },
    {$unwind: {path: "$resource"}},
    {
        $match: {
            $and: [
                {technology: {$ne: null}},
                {"name": "Spasmolgon"}
            ]
        }
    },
    {
        $project: {
            name: "$name",
            price: "$price",
            resourceName: "$resource.name",
            resourceAmount: "$technology.resources.count",
            resourcePrice: "$resource.price",
            _id: 0
        }
    }
);

// 10.1
db.orders.aggregate(
    {$unwind: {path: "$medicines"}},
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}}},
    {$match: {"medicine.name": {$in: ["Spasmolgon"]}}},
    {
        $group: {
            _id: {
                id: "$client._id",
                last_name: "$client.last_name"
            },
            count: {$count: {}}
        }
    },
    {
        $project: {
            id: "$_id.id",
            last_name: "$_id.last_name",
            count: 1,
            _id: 0
        }
    },
    {$sort: {count: -1}},
    {$limit: 10}
);

// 10.2
db.orders.aggregate(
    {$unwind: {path: "$medicines"}},
    {
        $lookup: {
            from: "clients",
            localField: "client.$id",
            foreignField: "_id",
            as: "client"
        }
    },
    {
        $lookup: {
            from: "medicines",
            localField: "medicines.medicine.$id",
            foreignField: "_id",
            as: "medicine"
        }
    },
    {
        $lookup: {
            from: "types",
            localField: "medicine.type.$id",
            foreignField: "_id",
            as: "type"
        }
    },
    {$addFields: {client: {$arrayElemAt: ["$client", 0]}}},
    {$match: {"type.name": {$in: ["maz", "krem"]}}},
    {
        $group: {
            _id: {
                id: "$client._id",
                last_name: "$client.last_name"
            },
            count: {$count: {}}
        }
    },
    {
        $project: {
            id: "$_id.id",
            last_name: "$_id.last_name",
            count: 1,
            _id: 0
        }
    },
    {$sort: {count: -1}},
    {$limit: 10}
)

db.medicine_shipping.find({}, {medicine: 1});