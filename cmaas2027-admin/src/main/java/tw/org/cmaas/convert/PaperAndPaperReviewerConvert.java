package tw.org.cmaas.convert;

import org.mapstruct.Mapper;

import tw.org.cmaas.pojo.DTO.PutPaperReviewDTO;
import tw.org.cmaas.pojo.VO.AssignedReviewersVO;
import tw.org.cmaas.pojo.VO.ReviewerScoreStatsVO;
import tw.org.cmaas.pojo.entity.PaperAndPaperReviewer;

@Mapper(componentModel = "spring")
public interface PaperAndPaperReviewerConvert {


	PaperAndPaperReviewer putDTOToEntity(PutPaperReviewDTO putPaperReviewDTO);

	AssignedReviewersVO entityToAssignedReviewersVO(PaperAndPaperReviewer paperAndPaperReviewer);

	ReviewerScoreStatsVO entityToReviewerScoreStatsVO(PaperAndPaperReviewer paperAndPaperReviewer);
}
