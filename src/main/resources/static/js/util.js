function inputPhoneNumber(event) {
    const phone = event.target;
    if (event.keyCode != 8) {
        const regExp = new RegExp(/^[0-9]{2,3}-^[0-9]{3,4}-^[0-9]{4}/g);
        if (event.target.value.replace(regExp, "").length != 0) {
            if (checkPhoneNumber(phone.value) == true) {
                let number = phone.value.replace(/[^0-9]/g, "");
                let tel = "";
                let seoul = 0;
                if (number.substring(0, 2).indexOf("02") == 0) {
                    seoul = 1;
                    phone.setAttribute("maxlength", "12");
                } else {
                    phone.setAttribute("maxlength", "13");
                }
                if (number.length < (4 - seoul)) {
                    return number;
                } else if (number.length < (7 - seoul)) {
                    tel += number.substr(0, (3 - seoul));
                    tel += "-";
                    tel += number.substr(3 - seoul);
                } else if (number.length < (11 - seoul)) {
                    tel += number.substr(0, (3 - seoul));
                    tel += "-";
                    tel += number.substr((3 - seoul), 3);
                    tel += "-";
                    tel += number.substr(6 - seoul);
                } else {
                    tel += number.substr(0, (3 - seoul));
                    tel += "-";
                    tel += number.substr((3 - seoul), 4);
                    tel += "-";
                    tel += number.substr(7 - seoul);
                }
                phone.value = tel;
            } else {
                const regExp = new RegExp(/[^0-9|^-]*$/);
                phone.value = phone.value.replace(regExp, "");
            }
        }
    }
}
function telValidator(args) {
    

    if (/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/.test(args)) {
        return true;
    }
    return false;
}

function formatInput(input) {
    // 입력된 값을 가져옵니다.
    let inputValue = input.value;

    // 입력값에서 숫자와 콤마를 제외한 모든 문자를 제거합니다.
    inputValue = inputValue.replace(/[^\d,]/g, '');

    // 콤마를 제외한 문자열을 숫자로 파싱합니다.
    let numericValue = parseInt(inputValue.replace(/,/g, ''));

    // 숫자를 다시 콤마 형식으로 포맷합니다.
    if (!isNaN(numericValue)) {
        input.value = numericValue.toLocaleString();
    } else {
        // 숫자로 변환할 수 없는 경우 입력값을 그대로 유지합니다.
        input.value = inputValue;
    }
}

function checkPhoneNumber(number) {
    const regExp = new RegExp(/^[0-9|-]*$/);
    if (regExp.test(number) == true) { return true; }
    else { return false; }
}


function validateNumberInput(event) {
    const input = event.target;
    input.value = input.value.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
}

function validateTextInput(event) {
    const input = event.target;
    input.value = input.value.replace(/[^\d가-힣]/g, ''); // 숫자와 한글 이외의 문자 제거
}

function validateNumSpecialInput(event) {
    const input = event.target;
    input.value = input.value.replace(/[^\d!@#$%^&*()_+,.?]/g, ''); // 특수문자와 숫자 이외의 문자 제거
}
function validateNumLowercaseInput(event) {
    const input = event.target;
    input.value = input.value.replace(/[^a-z0-9]/g, ''); // 소문자 또는 숫자가 아닌 문자를 제거합니다
}

/* 첨부파일 추가 */
function addFile(obj,type = "img") {
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수
    // 첨부파일 개수 확인
    if (remainFileCnt < 0) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
        return;
    }
    for (var i = 0; i < 1; i++) {

        const file = obj.files[i];

        // 첨부파일 검증
        if (validation(file,type)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function () {
                filesArr.push(file);
            };
            reader.readAsDataURL(file)

            // 목록 추가
            let htmlData = '';
            htmlData += '<li id="file' + fileNo + '" class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">X</a>';
            htmlData += '</li>';
            $('.file_list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 검증 */
function validation(obj,type) {
    const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif'];
    const adminFileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'application/pdf','application/vnd.ms-excel','application/msword'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (50 * 1024 * 1024)) {
        alert("최대 파일 용량인 50MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (type == 'img' && !fileTypes.includes(obj.type)) {
        alert("파일 확장자를 확인해주세요.");
        return false;
    } else if (type == 'admin' && !adminFileTypes.includes(obj.type)) {
        alert("파일 확장자를 확인해주세요.");
        return false;

    } else {
        return true;
    }
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
}

//미리보기이미지 로컬경로로 변경
function updatePreviewImage(fileInputId, previewImageId) {
    var fileInput = document.getElementById(fileInputId);
    var previewImage = document.getElementById(previewImageId);

    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result; // 선택한 파일의 내용으로 미리보기 이미지 업데이트
        };

        reader.readAsDataURL(fileInput.files[0]);
    } else {
        previewImage.src = ""; // 파일을 선택하지 않은 경우 미리보기 이미지 초기화
    }
}

function file_validation(obj) {
    const file = obj.files[0];
    if (!validation(file)) {
        obj.value = "";
    };
}


function enter_line(event,obj){
    if (event.key === "Enter" || event.keyCode === 13) {
        event.preventDefault(); // 기본 동작을 막음
        var currentText = $(obj).val();
        var selectionStart = obj.selectionStart;
        var selectionEnd = obj.selectionEnd;

        var newText = currentText.slice(0, selectionStart) + "\n" + currentText.slice(selectionEnd);

        $(obj).val(newText);

        obj.selectionStart = obj.selectionEnd = selectionStart + 1;
    }
}