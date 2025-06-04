// noinspection JSAnnotator

let jsonObject = {};

$(".table-responsive tbody tr").each(function (element) {
    key     = $(element).find("td").eq(0).text();
    value   = $(element).find("td").eq(1).text();
    jsonObject[key] = element;
    }
);

return JSON.stringify(jsonObject);