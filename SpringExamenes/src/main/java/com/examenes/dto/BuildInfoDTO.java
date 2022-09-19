package com.examenes.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildInfoDTO {
	String applicationName;
	String version;
	String lastBuild;
	String branchWithCommitId;
}
