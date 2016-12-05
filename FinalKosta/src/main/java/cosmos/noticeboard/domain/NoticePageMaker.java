package cosmos.noticeboard.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class NoticePageMaker {
	 private int totalCount; // 글갯수
	  private int startPage;
	  private int endPage;
	  private boolean prev; //이전
	  private boolean next; //이후

	  private int displayPageNum = 5; // 화면에 보여질 글 갯수 : 5개

	  private NoticeCriteria cri;

	  public void setCri(NoticeCriteria cri) {
	    this.cri = cri;
	  }

	  public void setTotalCount(int totalCount) {
	    this.totalCount = totalCount;

	    calcData();
	  }

	  private void calcData() {

	    endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

	    startPage = (endPage - displayPageNum) + 1;

	    int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

	    if (endPage > tempEndPage) {
	      endPage = tempEndPage;
	    }

	    prev = startPage == 1 ? false : true;

	    next = endPage * cri.getPerPageNum() >= totalCount ? false : true;

	  }

	  public int getTotalCount() {
	    return totalCount;
	  }

	  public int getStartPage() {
	    return startPage;
	  }

	  public int getEndPage() {
	    return endPage;
	  }

	  public boolean isPrev() {
	    return prev;
	  }

	  public boolean isNext() {
	    return next;
	  }

	  public int getDisplayPageNum() {
	    return displayPageNum;
	  }

	  public NoticeCriteria getCri() {
	    return cri;
	  }

	  public String makeQuery(int page) {

	    UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
	        .queryParam("perPageNum", cri.getPerPageNum()).build();

	    return uriComponents.toUriString();
	  }
	  
	  
	  public String makeSearch(int page){
	    
	    UriComponents uriComponents =
	              UriComponentsBuilder.newInstance()
	              .queryParam("page", page)
	              .queryParam("perPageNum", cri.getPerPageNum())
	              .queryParam("searchType", ((NoticeSearchCriteria)cri).getSearchType())
	              .queryParam("keyword", ((NoticeSearchCriteria)cri).getKeyword())
	              .build();             
	    
	    return uriComponents.toUriString();
	  }
}
