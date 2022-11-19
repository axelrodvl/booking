import callBackend from "./utils";

export async function sendReview(name, phone, review, isPublic) {
    let uri = '/api/review';
    let body = {
        name: name,
        phone: phone,
        review: review,
        isPublic: isPublic
    }

    let headers = {
        "Content-Type": "application/json"
    }

    return callBackend(uri, body, headers)
}

export async function sendRequest(name, phone, note) {
    let uri = '/api/request';
    let body = {
        name: name,
        phone: phone,
        note: note
    }

    let headers = {
        "Content-Type": "application/json"
    }

    return callBackend(uri, body, headers)
}