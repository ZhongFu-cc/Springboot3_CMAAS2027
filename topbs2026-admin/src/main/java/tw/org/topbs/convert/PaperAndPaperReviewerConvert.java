package tw.org.topbs.convert;

import org.mapstruct.Mapper;

import tw.org.topbs.pojo.DTO.PutPaperReviewDTO;
import tw.org.topbs.pojo.VO.AssignedReviewersVO;
import tw.org.topbs.pojo.VO.ReviewerScoreStatsVO;
import tw.org.topbs.pojo.entity.PaperAndPaperReviewer;

@Mapper(componentModel = "spring")
public interface PaperAndPaperReviewerConvert {


	PaperAndPaperReviewer putDTOToEntity(PutPaperReviewDTO putPaperReviewDTO);

	AssignedReviewersVO entityToAssignedReviewersVO(PaperAndPaperReviewer paperAndPaperReviewer);

	ReviewerScoreStatsVO entityToReviewerScoreStatsVO(PaperAndPaperReviewer paperAndPaperReviewer);
}
