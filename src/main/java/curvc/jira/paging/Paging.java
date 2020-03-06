package curvc.jira.paging;

public class Paging {
	
	public static String getPageBar(int total, int cPage, int numPerPage) {
		String pageBar = "";
	
		int totalPage = (int)Math.ceil((double)total/numPerPage);
		int pageBarSize = 5; //페이지 바 사이즈
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		pageBar += "<ul class='pagination'>";
		
		if(pageNo==1) {
			pageBar += "<li class='page-link'>이전</li>";
		} else {
			pageBar += "<li class='page-link' onclick='fn_paging(\"" + (pageNo-1) + "\")'>이전</li>";
		}
		
		while(!(pageNo>pageEnd || pageNo > totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<li class='page-link'>" + pageNo + "</li>";
			} else {
				pageBar += "<li class='page-link' onclick='fn_paging(\"" + pageNo + "\")'>" + pageNo + "</li>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<li class='page-link'>다음</li>";
		} else {
			pageBar += "<li class='page-link' onclick='fn_paging(\"" + pageNo + "\")'>다음</li>";
		}
		
		pageBar += "</ul>";
		return pageBar;
	}
	
}
