// use pharmacy;

// query 1
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
    {
        $project: {
            _id: 0, id: "$_id.id", last_name: "$_id.last_name",
            first_name: "$_id.first_name", status: 1, count: 1
        }
    }
);

// 2
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
    {
        $project: {
            _id: 0, id: "$_id.id", last_name: "$_id.last_name",
            first_name: "$_id.first_name", status: 1
        }
    }
);

// 3
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
        $project: {
            medicineName: {$arrayElemAt: ["$medicine.name", 0]},
            medicineAmount: "$medicines.amount"
        }
    },
    {$group: {_id: "$medicineName", count: {$sum: "$medicineAmount"}}},
    {$sort: {count: -1}},
    {$limit: 10}
);

// 4
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
    {
        $project: {
            _id: 0, id: "$_id.id", last_name: "$_id.last_name",
            first_name: "$_id.first_name", count: 1
        }
    }
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
);

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

// 7
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

// 8
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

// 9
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

// 10
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
    {$group: {_id: {id: "$client._id", last_name: "$client.last_name"}, count: {$count: {}}}
    },
    {$project: {id: "$_id.id", last_name: "$_id.last_name", count: 1, _id: 0}},
    {$sort: {count: -1}},
    {$limit: 10}
);